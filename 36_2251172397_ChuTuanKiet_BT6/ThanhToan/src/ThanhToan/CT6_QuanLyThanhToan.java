package ThanhToan;

// Lớp chính để mô phỏng hệ thống thanh toán
public class CT6_QuanLyThanhToan {
    public static void main(String[] args) {
        // Tạo đối tượng thanh toán tiền mặt
        IThanhToan thanhToanTienMat = new ThanhToanTienMat();

        // Tạo đối tượng thanh toán thẻ tín dụng
        IThanhToan thanhToanTheTinDung = new ThanhToanTheTinDung();

        // Số tiền cụ thể để thanh toán
        double soTien1 = 1500000.0;
        double soTien2 = 2750000.0;

        // Mô phỏng thanh toán tiền mặt
        System.out.println("--- Thanh toán tiền mặt ---");
        thanhToanTienMat.ThanhToan(soTien1);

        // Mô phỏng thanh toán thẻ tín dụng
        System.out.println("\n--- Thanh toán thẻ tín dụng ---");
        thanhToanTheTinDung.ThanhToan(soTien2);
    }
}
