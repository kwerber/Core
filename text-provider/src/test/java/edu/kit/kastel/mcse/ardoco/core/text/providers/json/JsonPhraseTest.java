/* Licensed under MIT 2022. */
package edu.kit.kastel.mcse.ardoco.core.text.providers.json;

import java.io.File;
import java.io.IOException;

import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.kit.kastel.mcse.ardoco.core.api.data.text.IPhrase;
import edu.kit.kastel.mcse.ardoco.core.api.data.text.ISentence;
import edu.kit.kastel.mcse.ardoco.core.api.data.text.PhraseType;

class JsonPhraseTest {

    protected static String path = "src/test/resources/teastore.json";

    public static final int SENTENCE_NO = 41;
    private static IPhrase npPhrase;
    private static IPhrase vpPhrase;
    private static IPhrase sentencePhrase;

    private static ISentence sentence;

    @BeforeAll
    static void beforeAll() throws IOException {
        var textProvider = JsonTextProvider.loadFromFile(new File(path));
        var text = textProvider.getAnnotatedText();
        var textSentence = text.getSentences().get(SENTENCE_NO);
        sentence = textSentence;
        ImmutableList<IPhrase> phrases = textSentence.getPhrases();
        sentencePhrase = phrases.get(1);
        vpPhrase = phrases.get(3);
        npPhrase = phrases.get(4);
    }

    @Test
    void getPhraseTypeTest() {
        Assertions.assertAll( //
                () -> Assertions.assertEquals(PhraseType.S, sentencePhrase.getPhraseType()), //
                () -> Assertions.assertEquals(PhraseType.VP, vpPhrase.getPhraseType()), //
                () -> Assertions.assertEquals(PhraseType.NP, npPhrase.getPhraseType()) //
        );
    }

    @Test
    void getSentenceNoTest() {
        Assertions.assertAll(//
                () -> Assertions.assertEquals(SENTENCE_NO, npPhrase.getSentenceNo()), //
                () -> Assertions.assertEquals(SENTENCE_NO, vpPhrase.getSentenceNo()), //
                () -> Assertions.assertEquals(SENTENCE_NO, sentencePhrase.getSentenceNo()));
    }

    @Test
    void getSentenceTest() {
        Assertions.assertEquals(sentence, npPhrase.getSentence());
    }

    @Test
    void getTextTest() {
        var sentencePhraseText = sentencePhrase.getText();
        var npPhraseText = npPhrase.getText();
        var vpPhraseText = vpPhrase.getText();
        Assertions.assertAll( //
                () -> Assertions.assertEquals("The TeaStore is a test application.", sentencePhraseText), //
                () -> Assertions.assertEquals("a test application", npPhraseText), //
                () -> Assertions.assertEquals("is a test application", vpPhraseText)); //
    }

    @Test
    void getContainedWords() {
        var words = npPhrase.getContainedWords();
        Assertions.assertAll(//
                () -> Assertions.assertEquals(3, words.size()), //
                () -> Assertions.assertEquals(738, words.get(0).getPosition()), //
                () -> Assertions.assertEquals(740, words.get(words.size() - 1).getPosition()), //
                () -> Assertions.assertEquals("test", words.get(1).getText()), //
                () -> Assertions.assertEquals(sentence.getWords().get(3), words.get(0)));
    }

    @Test
    void isSuperPhraseOfTest() {
        Assertions.assertAll(//
                () -> Assertions.assertTrue(sentencePhrase.isSuperPhraseOf(vpPhrase)), //
                () -> Assertions.assertTrue(sentencePhrase.isSuperPhraseOf(npPhrase)), //
                () -> Assertions.assertTrue(vpPhrase.isSuperPhraseOf(npPhrase)), //
                () -> Assertions.assertFalse(npPhrase.isSuperPhraseOf(vpPhrase)), //
                () -> Assertions.assertFalse(npPhrase.isSuperPhraseOf(sentencePhrase)), //
                () -> Assertions.assertFalse(vpPhrase.isSuperPhraseOf(sentencePhrase)), //
                () -> Assertions.assertFalse(npPhrase.isSuperPhraseOf(npPhrase)), //
                () -> Assertions.assertFalse(vpPhrase.isSuperPhraseOf(vpPhrase)), //
                () -> Assertions.assertFalse(sentencePhrase.isSuperPhraseOf(sentencePhrase)));

    }

    @Test
    void isSubPhraseOfTest() {
        Assertions.assertAll(//
                () -> Assertions.assertTrue(vpPhrase.isSubPhraseOf(sentencePhrase)), //
                () -> Assertions.assertTrue(npPhrase.isSubPhraseOf(sentencePhrase)), //
                () -> Assertions.assertTrue(npPhrase.isSubPhraseOf(vpPhrase)), //
                () -> Assertions.assertFalse(vpPhrase.isSubPhraseOf(npPhrase)), //
                () -> Assertions.assertFalse(sentencePhrase.isSubPhraseOf(npPhrase)), //
                () -> Assertions.assertFalse(sentencePhrase.isSubPhraseOf(vpPhrase)), //
                () -> Assertions.assertFalse(npPhrase.isSubPhraseOf(npPhrase)), //
                () -> Assertions.assertFalse(vpPhrase.isSubPhraseOf(vpPhrase)), //
                () -> Assertions.assertFalse(sentencePhrase.isSubPhraseOf(sentencePhrase)));
    }

    @Test
    void getSubPhrasesTest() {
        var sentenceSubPhrases = sentencePhrase.getSubPhrases();
        var vpSubPhrases = vpPhrase.getSubPhrases();
        var npSubPhrases = npPhrase.getSubPhrases();

        Assertions.assertAll(//
                () -> Assertions.assertEquals(3, sentenceSubPhrases.size()), //
                () -> Assertions.assertEquals(1, vpSubPhrases.size()), //
                () -> Assertions.assertEquals(0, npSubPhrases.size()));
    }
}
