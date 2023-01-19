
import org.example.MyClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MyClassTests {

    @BeforeAll
    public static void before() {
        System.out.println("Началось тестирование!");
    }

    @AfterAll
    public static void after() {
        System.out.println("Тестирование успешно" +
                " завершено, вы молодец!");
    }

    @Test
    public void test1() {
        // given:
        int a = 3, resault = 9;
        System.out.println("..");
        MyClass myClass = new MyClass();
        // when:
        int newResault = myClass.square(a);
        // then:
        Assertions.assertEquals(resault,newResault);

    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(1,1,1),
                Arguments.of(3,9,27));
    }

    @MethodSource("source")
    @ParameterizedTest
    public void test2(int a, int b, int c) {
        // given:
        MyClass myClass = new MyClass();
        // when:
        int resault = myClass.cube(a);
        System.out.println("..");
        // then:
        Assertions.assertEquals(resault,c);
    }

    @MethodSource("source")
    @ParameterizedTest
    public void test3(int a,int b) {
        // assert:
        MyClass myClass = new MyClass();
        // act:
        int resault = myClass.square(a);
        System.out.println("..");
        // assert:
        Assertions.assertEquals(resault,b);
    }

    @MethodSource("source")
    @ParameterizedTest
    public void hamcrest1(int a) {
        // assert:
        MyClass myClass = new MyClass();
        // act:
        String string = myClass.toString(a);
        System.out.println("..");
        // assert:
        assertThat(string, anyOf(containsString("число"), endsWith(a + " ")));
    }

    @MethodSource("source")
    @ParameterizedTest
    public void hamcrest2(int a, int b, int c) {
        // assert:
        // act:
        // assert:
        assertThat(a, allOf(greaterThan(0), lessThanOrEqualTo(c)));
    }
}
