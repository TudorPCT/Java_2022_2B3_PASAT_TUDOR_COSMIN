package com.company;

public interface Storage {
    int getStorageCapacity();
    void setStorageCapacity(int newStorage);

    default long getStorageCapacityAlternative(String unit) {
        if(unit.compareTo("megabyte") == 0)
            return getStorageCapacity() * 1024;
        else if(unit.compareTo("kilobyte") == 0)
            return getStorageCapacity() * 1_048_576;
        else if(unit.compareTo("byte") == 0)
            return getStorageCapacity() * 1_073_741_824;
        return 0;
    }
}
