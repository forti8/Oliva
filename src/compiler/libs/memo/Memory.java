package compiler.libs.memo;

public class Memory {
    static {
        System.loadLibrary("memo");
    }

    private static native long nativeAlloc(int tamanho); 
    private static native int nativeFree (long local);

    private static native int nativeWriteInt (long ptr, int valor);
    private static native int nativeWriteChar (long ptr, char valor);
    private static native int nativeWriteBool (long ptr, boolean valor);
    private static native int nativeWriteFloat (long ptr, float valor);

    private static native int nativeReadInt (long ptr);
    private static native char nativeReadChar (long ptr);
    private static native boolean nativeReadBool (long ptr);
    private static native float nativeReadFloat (long ptr);

    public static void main (String[] args) {
    };

    public static long Alloc (int tamanho) {
        long ptrMemoAlloc = nativeAlloc(tamanho);
        return ptrMemoAlloc;
    }

    public static int Free (long local) {
        nativeFree(local);
        return 0;
    }

    // write in memory
    public static int WriteInt (long local, int valor) {
        return nativeWriteInt(local, valor);
    }
    public static int WriteChar (long local, char valor) {
        return nativeWriteChar(local, valor);
    }
    public static int WriteFloat (long local, float valor) {
        return nativeWriteFloat(local, valor);
    }
    public static int WriteBool (long local, boolean valor) {
        return nativeWriteBool(local, valor);
    }

    // read in memory
    public static int ReadInt (long local) {
        return nativeReadInt(local);
    }
    public static char ReadChar (long local) {
        return nativeReadChar(local);
    }
    public static float ReadFloat (long local) {
        return nativeReadFloat(local);
    }
    public static boolean ReadBool (long local) {
        return nativeReadBool(local);
    }
}