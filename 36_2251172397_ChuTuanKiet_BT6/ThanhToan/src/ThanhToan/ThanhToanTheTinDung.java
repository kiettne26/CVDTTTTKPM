package ThanhToan;

public class ThanhToanTheTinDung implements IThanhToan {
    @Override
    public void ThanhToan(double soTien) {
        System.out.println("Thanh toán bằng thẻ tín dụng số tiền: " + soTien + " VND");
    }
}
