public class CpuLoad {
    public static void main(String[] args) {
        long end = System.currentTimeMillis() + 60000;

        while(System.currentTimeMillis() < end){
            double x = Math.sqrt(Math.random());
        }

        System.out.println("CPU Load completed");
    }
}
