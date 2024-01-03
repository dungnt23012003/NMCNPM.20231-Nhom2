package src.main.entity;

public class CoSoVatChat {
    public String maCSVC = "";
    public int soLuong = 0;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CoSoVatChat castedObj) {
            return maCSVC.equals(castedObj.maCSVC);
        }
        return false;
    }
}
