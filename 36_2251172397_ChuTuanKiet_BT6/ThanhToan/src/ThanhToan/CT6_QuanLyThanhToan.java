package ThanhToan;

// lớp chính để mô phỏng hệ thống thanh toán
public class CT6_QuanLyThanhToan {
    public static void main(String[] args) {
        IThanhToan ThanhToanTienMat = new ThanhToanTienMat();
        IThanhToan ThanhToanTheTinDung = new ThanhToanTheTinDung(
                "26-09-2004", "CHU TUAN KIET", "29/12/2025"
        );
        // Thanh Toán tiền mặt
        double TienMat = 250000;
        ThanhToanTienMat.ThanhToan(TienMat);
        ThanhToanTienMat.HienThi(TienMat);

        // Thanh Toán thẻ tín dụng
        double tttienThe = 25000;
        ThanhToanTheTinDung.ThanhToan(tttienThe);
        ThanhToanTheTinDung.HienThi(tttienThe);
    }
}
