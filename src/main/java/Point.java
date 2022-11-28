import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;



@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "points")
@Table(name = "points")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Point implements Serializable, Cloneable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "PointsIdSeq", sequenceName = "points_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PointsIdSeq")
    private Integer id;

    @Column(name = "y", nullable = false)
    private String y;

    @Column(name = "x", nullable = false)
    private String x;

    @Column(name = "r", nullable = false)
    private String r;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "time", nullable = false)
    private String time;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}