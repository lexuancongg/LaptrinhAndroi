package com.lexuancong.demo.listener;

import com.lexuancong.demo.model.ParentEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configurable

public class CustomAuditingEntityListener extends AuditingEntityListener {
    public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> handler) {
        super.setAuditingHandler(handler);
    }

    public CustomAuditingEntityListener() {

    }

    @Override
    public void touchForCreate(Object target) {
        ParentEntity entity = (ParentEntity) target;

        if (entity.getCreatedBy() == null) {
            super.touchForCreate(target);
        } else {
            if (entity.getLastModifiedBy() == null) {
                entity.setLastModifiedBy(entity.getCreatedBy());
            }
        }
    }
    @Override
    public void touchForUpdate(Object target) {
        ParentEntity entity = (ParentEntity) target;
        if (entity.getLastModifiedBy() == null) {
            super.touchForUpdate(target);
        }
    }
}
