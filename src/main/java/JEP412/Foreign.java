package JEP412;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SymbolLookup;

import java.lang.invoke.MethodType;
import java.util.Objects;

public class Foreign {

    private static final SymbolLookup libLookup;

    static {
        // loads a particular C library
        var path = Objects.requireNonNull(Foreign.class.getResource("/external-library")).getPath();
        System.load(path);
        libLookup = SymbolLookup.loaderLookup();
    }

    public static void main(String[] args) {

        var printMethod = libLookup.lookup("hello-world");

        if (printMethod.isPresent()) {
            var methodReference = CLinker.getInstance()
                    .downcallHandle(
                            printMethod.get(),
                            MethodType.methodType(MemoryAddress.class, MemoryAddress.class),
                            FunctionDescriptor.of(CLinker.C_POINTER, CLinker.C_POINTER)
                    );

            try {
                var nativeString = CLinker.toCString("Matej", ResourceScope.newImplicitScope());
                var invokeReturn = methodReference.invoke(nativeString.address());
                var memoryAddress = (MemoryAddress) invokeReturn;
                CLinker.toJavaString(memoryAddress);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
        throw new RuntimeException("printName function not found.");

    }
}
