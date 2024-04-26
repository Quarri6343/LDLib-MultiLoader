package com.lowdragmc.lowdraglib.syncdata.accessor;

import com.lowdragmc.lowdraglib.syncdata.AccessorOp;
import com.lowdragmc.lowdraglib.syncdata.IAccessor;
import com.lowdragmc.lowdraglib.syncdata.managed.IManagedVar;
import com.lowdragmc.lowdraglib.syncdata.managed.IRef;
import com.lowdragmc.lowdraglib.syncdata.managed.ManagedRef;
import com.lowdragmc.lowdraglib.syncdata.payload.ITypedPayload;
import com.lowdragmc.lowdraglib.syncdata.payload.PrimitiveTypedPayload;
import net.minecraft.core.HolderLookup;

public abstract class ManagedAccessor implements IAccessor {
    private byte defaultType = -1;

    @Override
    public byte getDefaultType() {
        return defaultType;
    }

    @Override
    public void setDefaultType(byte defaultType) {
        this.defaultType = defaultType;
    }

    @Override
    public boolean isManaged() {
        return true;
    }

    public abstract ITypedPayload<?> readManagedField(AccessorOp op, IManagedVar<?> field, HolderLookup.Provider provider);

    public abstract void writeManagedField(AccessorOp op, IManagedVar<?> field, ITypedPayload<?> payload, HolderLookup.Provider provider);

    @Override
    public ITypedPayload<?> readField(AccessorOp op, IRef field, HolderLookup.Provider provider) {
        if (!(field instanceof ManagedRef syncedField)) {
            throw new IllegalArgumentException("Field %s is not a managed field".formatted(field));
        }
        var managedField = syncedField.getField();
        if (!managedField.isPrimitive() && managedField.value() == null) {
            return PrimitiveTypedPayload.ofNull();
        }
        return readManagedField(op, managedField, provider);
    }


    @Override
    public void writeField(AccessorOp op, IRef field, ITypedPayload<?> payload, HolderLookup.Provider provider) {
        if (!(field instanceof ManagedRef syncedField)) {
            throw new IllegalArgumentException("Field %s is not a managed field".formatted(field));
        }
        var managedField = syncedField.getField();
        writeManagedField(op, managedField, payload, provider);
    }


}
