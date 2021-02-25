public class Operands {

    private int a, b;

    public Operands(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Operands(double a, double b) {
        this.a = (int) a;
        this.b = (int) b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
