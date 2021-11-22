package ch10c;

import java.util.List;

public class JukeboxData {
    class Songs {

        public List<Song> getSongs() {
            return List.of(
                    new Song("somersault", "zero 7", 50),
                    new Song("cassidy", "grateful dead", 65),
                    new Song("$10", "hitchhiker", 80),
                    new Song("havana", "cabello", 60),
                    new Song("Cassidy", "grateful dead", 65),
                    new Song("50 ways", "simon", 70),
                    new Song("If There's a Rocket Tie Me to It", "Snow Patrol", 140),
                    new Song("Hurt", "Nine Inch Nails", 79),
                    new Song("Hurt", "Johnny Cash", 91),
                    new Song("Watercolour", "Pendulum", 174),
                    new Song("The Outsider", "A Perfect Circle", 113),
                    new Song("The Turning", "Oasis", 120),
                    new Song("With a Little Help from My Friends", "The Beatles", 112),
                    new Song("Come Together", "The Beatles", 92),
                    new Song("Come Together", "The Smokin' Mojo Filters", 176),
                    new Song("With a Little Help from My Friends", "Joe Cocker", 72),
                    new Song("Immigrant Song", "Karen O feat Trent Reznor and Atticus Ross", 120),
                    new Song("Girls Just Want to Have Fun", "Cyndi Lauper", 123),
                    new Song("High Hopes", "Panic! at the Disco", 164),
                    new Song("Machine", "Imagine Dragons", 81),
                    new Song("Breathe", "The Prodigy", 130),
                    new Song("Hallucinate", "Dua Lipa", 122),
                    new Song("Walk Me Home", "P!nk", 88),
                    new Song("I am not a woman, I'm a god", "Halsey", 180),
                    new Song("Pasos de cero", "Pablo Alborán", 99),
                    new Song("Immigrant song", "Led Zeppelin", 112));
        }
    }
}
