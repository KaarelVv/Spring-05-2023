package ee.kaarel.nordpool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // paneb kõik getterid
@Setter // paneb kõik setterid
@AllArgsConstructor // paneb konstrukturi
// puhta koodi jaoks. Aga kui vaja lisda meetodile funksiooni siis lihtsalt lisa meetod funksiooniga
public class Person {
    private Long id;
    private String firsName;
    private String lastName;
    private String phoneNumber;


}
