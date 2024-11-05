package com.fpetrola.z80.bytecode.refactor;

import com.fpetrola.z80.minizx.JetSetWilly;
import org.objectweb.asm.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.objectweb.asm.Opcodes.*;

public class MethodDuplicatorTransformer extends ClassVisitor {
    private final String originalMethodName;
    private final String newMethodPrefix;

    public MethodDuplicatorTransformer(ClassVisitor classVisitor, String originalMethodName, String newMethodPrefix) {
        super(ASM9, classVisitor);
        this.originalMethodName = originalMethodName;
        this.newMethodPrefix = newMethodPrefix;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

        // Check if this is the method we want to duplicate
        if (name.equals(originalMethodName)) {
            // Transform the original method by creating a duplicate with modified field accesses
            return new MethodDuplicator(mv, access, name, descriptor);
        }

        return mv;
    }

    class MethodDuplicator extends MethodVisitor {
        private final Map<String, String> fieldsToParams = new LinkedHashMap<>(); // Fields that will become parameters
      private final String descriptor;

      public MethodDuplicator(MethodVisitor mv, int access, String name, String descriptor) {
            super(ASM9, mv);
        this.descriptor = descriptor;
      }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
            if (opcode == GETFIELD || opcode == PUTFIELD) {
                // If it's a field access, add it to the map of fields to convert to parameters
                fieldsToParams.putIfAbsent(name, descriptor);
            }
            super.visitFieldInsn(opcode, owner, name, descriptor);
        }

        @Override
        public void visitEnd() {
            // Create the descriptor for the new method with additional parameters for fields
            Type originalMethodType = Type.getMethodType(descriptor);
            StringBuilder newDescriptor = new StringBuilder("(");

            // Append original method parameters
            for (Type argType : originalMethodType.getArgumentTypes()) {
                newDescriptor.append(argType.getDescriptor());
            }

            // Append parameters for each field
            for (String fieldDescriptor : fieldsToParams.values()) {
                newDescriptor.append(fieldDescriptor);
            }

            newDescriptor.append(")").append(originalMethodType.getReturnType().getDescriptor());
            String newMethodDescriptor = newDescriptor.toString();

            // Create the new method with the modified descriptor
            MethodVisitor newMethod = cv.visitMethod(ACC_PUBLIC, newMethodPrefix + originalMethodName, newMethodDescriptor, null, null);
            newMethod.visitCode();

            // Create a map for the new parameters, mapping each field to its new parameter index
            int paramIndex = originalMethodType.getArgumentTypes().length;
            Map<String, Integer> fieldToParamIndex = new LinkedHashMap<>();
            for (String fieldName : fieldsToParams.keySet()) {
                fieldToParamIndex.put(fieldName, paramIndex);
                paramIndex++;
            }

            // Copy instructions, replacing field accesses with parameter accesses
            MethodVisitor methodVisitor = new MethodVisitor(ASM9, newMethod) {
                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                    Integer paramIndex = fieldToParamIndex.get(name);

                    if (opcode == GETFIELD && paramIndex != null) {
                        // Replace GETFIELD with loading the corresponding parameter
                        newMethod.visitVarInsn(ALOAD, paramIndex);
                    } else if (opcode == PUTFIELD && paramIndex != null) {
                        // Replace PUTFIELD with storing into the corresponding parameter
                        newMethod.visitVarInsn(ALOAD, paramIndex);
                    } else {
                        // Otherwise, copy the original field instruction
                        super.visitFieldInsn(opcode, owner, name, descriptor);
                    }
                }

                @Override
                public void visitInsn(int opcode) {
                    super.visitInsn(opcode);
                }

                @Override
                public void visitVarInsn(int opcode, int var) {
                    super.visitVarInsn(opcode, var);
                }
            };

            newMethod.visitMaxs(0, 0);
            newMethod.visitEnd();

            super.visitEnd();
        }
    }

    public static void main(String[] args) throws Exception {
        // Load the original class
        ClassReader classReader = new ClassReader(JetSetWilly.class.getName());
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        // Apply the transformation to duplicate a method with field accesses replaced by parameters
        MethodDuplicatorTransformer transformer = new MethodDuplicatorTransformer(classWriter, "$38601", "newMethodPrefix");
        classReader.accept(transformer, 0);

        // Save the modified class
        byte[] modifiedClass = classWriter.toByteArray();
        // Save or load the class as needed

        byte[] bytecode = classWriter.toByteArray();
        Files.write(Path.of("jsw1.class"), bytecode);
    }
}
