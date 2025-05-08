package ThanhToan;

public class ThanhToanTienMat implements IThanhToan {
    @Override
    public void ThanhToan(double soTien) {
        System.out.println("Thanh toán tiền mặt: " + soTien + " VNĐ");
    }

    @Override
    public void HienThi(double soTien) {
        System.out.println("Thanh toán tiền mặt thành công " + soTien + " VNĐ");
    }
}
