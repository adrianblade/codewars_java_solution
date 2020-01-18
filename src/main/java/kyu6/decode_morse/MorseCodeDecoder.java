package kyu6.decode_morse;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MorseCodeDecoder {

    private static final String ONE_SPACE = " ";
    private static final String THREE_SPACES = " {3}";

    public static String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        // your brilliant code here, remember that you can access the preloaded Morse code table through MorseCode.get(code)
        String[] words = morseCode.split(THREE_SPACES);
        for (String word : words) {
            String[] letters = word.split(ONE_SPACE);
            for (String letter : letters) {
                String translation = MorseCode.get(letter);
                if (translation != null) result.append(translation);
            }
            if (result.length() > 0) {
                result.append(ONE_SPACE);
            }
        }

        return result.toString().trim();
    }

    public static String decode2(String morseCode) {
        return Arrays.stream(morseCode.split(THREE_SPACES))
                .map(i -> Arrays.stream(i.split(ONE_SPACE))
                        .map(MorseCode::get)
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "))
                .trim();
    }

    public static String decode3(String morseCode) {
        return Arrays.stream(morseCode.trim().split(THREE_SPACES))
                .map(MorseCodeDecoder::decodeWord)
                .collect(Collectors.joining(" "));
    }

    private static String decodeWord(String morseWord) {
        return Arrays.stream(morseWord.trim().split(ONE_SPACE))
                .map(MorseCode::get)
                .collect(Collectors.joining());
    }
}
