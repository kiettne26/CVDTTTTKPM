package ThanhToan;

public class ThanhToanTheTinDung implements IThanhToan {
    private String SoThe;
    private String HoTen;
    private String NgayHetHan;

    ThanhToanTheTinDung(String SoThe, String HoTen, String NgayHetHan) {
        this.SoThe = SoThe;
        this.HoTen = HoTen;
        this.NgayHetHan = NgayHetHan;
    }

    @Override
    public void ThanhToan(double soTien) {
        System.out.println("Số thẻ: " + SoThe);
        System.out.println("Họ tên: " + HoTen);
        System.out.println("Ngày hết hạn: " + NgayHetHan);
    }

    @Override
    public void HienThi(double soTien) {
        System.out.println("Thanh toán thẻ tín dụng thành công " + soTien + " VNĐ");
    }
}
