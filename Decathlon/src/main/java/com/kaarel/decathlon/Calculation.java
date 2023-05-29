package com.kaarel.decathlon;

import lombok.Getter;
import lombok.Setter;

//		Ala 				A 			B 		C
//		100 m jooks 		25,4347 	18 		1.81   P -tulemus
//		Kaugushüpe 			0,14354 	220 	1.4
//		Kuulitõuge 			51,39 		1.5 	1.05
//		Kõrgushüpe 			0,8465 		75 		1.42
//		400 m jooks 		1,53775 	82 		1.81
//		110 m tõkkejooks 	5,74352 	28.5 	1.92
//		Kettaheide 			12,91 		4 		1.1
//		Teivashüpe 			0,2797 		100 	1.35
//		Odavise 			10,14 		7 		1.08
//		1500 m jooks 		0,03768 	480 	1.85
//    Points = INT(A(B — P)C) for track events (faster time produces a higher score)
//    Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)
@Setter
@Getter
public class Calculation {

    private Fields fields ;
    private Long value;

    public Calculation(Athlete athlete) {
        this.fields = fields;

        switch (fields) {
            case HUNDRED_METERS -> {value = (long) Math.pow((25.4347 * (18 - athlete.getResult())), 1.81);
            }
            case LONG_JUMP -> {value = (long) Math.pow((0.14354 * (220 - athlete.getResult())), 1.4);
            }
            case SHOT_PUT -> {value = (long) Math.pow((51.39  * (1.5 - athlete.getResult())), 1.05);
            }
            case HIGH_JUMP -> {value = (long) Math.pow((0.8465 * (75 - athlete.getResult())), 1.42);
            }
            case FOUR_HUNDRED_METERS -> {value = (long) Math.pow((1.53775 * (82 - athlete.getResult())), 1.81);
            }
            case HURDLES -> {value = (long) Math.pow((5.74352 * (28.5 - athlete.getResult())), 1.92);
            }
            case DISCUS_THROW -> {value = (long) Math.pow((25.4347 * (18 - athlete.getResult())), 1.1);
            }
            case POLE_VAULT -> {value = (long) Math.pow((0.2797 * (100 - athlete.getResult())), 1.35);
            }
            case JAVELIN_THROW -> {value = (long) Math.pow((10.14 * (7 - athlete.getResult())), 1.08);
            }
            case FIFTEEN_HUNDRED_METERS -> {value = (long) Math.pow((25.4347 * (18 - athlete.getResult())), 1.81);
            }
            default -> throw new IllegalArgumentException("Unsupported field: " + fields);

        }
    }
}
