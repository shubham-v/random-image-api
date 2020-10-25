package hackerearth.contest.randomimage.api.core.entities;

import hackerearth.contest.randomimage.api.core.entities.base.BaseAbstractAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseAbstractAuditableEntity {

    private String requestId;
    private String url;

    @Override
    public String toString() {
        return url;
    }
}
