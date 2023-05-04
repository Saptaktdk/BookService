package in.saptak.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "books")
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private BigDecimal price;


}
