Data configuration:
This project uses an in-memory DB for creating schemas and initial data

schemas:
```
CREATE TABLE IF NOT EXISTS reservations (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
client_full_name VARCHAR(255) NOT NULL,
room_number INT NOT NULL
);

CREATE TABLE IF NOT EXISTS reservation_dates (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
date DATE,
reservation_id INT,
FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);
```
Application then uses Spring data and Hibernate to create a repository and then an entity class:

````@Entity(name="reservations")
public class Reservation
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

    private String clientFullName;

    private Integer roomNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private List<ReservationDate> reservationDates;
}

@Entity(name="reservation_dates")
public class ReservationDate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}
`````

`ReservationController` Has 3 endpoints for creating, retrieving and updating data.
Omitted creating `@Service` class to avoid making the app to complex. But in real world app we would need to implement service layer

`GET /reservations`
`PUT /reservations/{id}`
`POST /reservations`

Then I added a couple of Integration tests in test folder:

`com.alan.test.javainterview.controller.ReservationControllerTest`

This test class implements

`@SpringBootTest` and`@ExtendWith(SpringExtension.class)` in order to create an instance of the application with profile 'local' as defined in .yml file.

Finally, I implemented .yml environment files so we can define proper configuration for desired environments. eg: dev, test, stage and prod