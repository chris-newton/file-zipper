import java.io.File;

class Driver {
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        
        
        File src1 = new File("C:\\Users\\newto\\Desktop\\Compression\\data\\lorem.txt");
        File dst1 = new File("C:\\Users\\newto\\Desktop\\Compression\\output");
        
        Compression.compress(src1, dst1);
        
        File src2 = new File("C:\\Users\\newto\\Desktop\\Compression\\output\\lorem_compressed.txt");
        File dst2 = new File("C:\\Users\\newto\\Desktop\\Compression\\output");
        
        Compression.decompress(src2, dst2);
    }
}