package src.main.entity;

public class PhongBan {
    public String maPhongBan = "";

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PhongBan castedObj) {
            return maPhongBan.equals(castedObj.maPhongBan);
        }
        return false;
    }
}
