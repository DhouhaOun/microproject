package micro.project.customerservice.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
}
