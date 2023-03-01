package de.telran.firstspringbootdemo.domain.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@With
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue
    UUID id;
    //    @ManyToOne
//    @JoinColumn(name = "user_id")
//    User user;
    String header;
    String text;
    @Column(name = "created", updatable = false)
    @CreationTimestamp
//    @CreatedBy
    OffsetDateTime created;
    @UpdateTimestamp
//    @LastModifiedBy
    OffsetDateTime updated;

//    @PrePersist
//    @PostPersist
//    @PreUpdate
//    @PreRemove
//    @PreDestroy
//    @PostRemove
    public void doSomethingAfterUpdate() {

    }
}
