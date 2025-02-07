package inventory;

/**
 * Represents the generic stock for the application. It contains the medicine name and quantity.
 * It is inherited by Dispense, Medicine and Order objects.
 */

public abstract class Medicine {
    protected String medicineName;
    protected int quantity;

    public Medicine(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
