import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class App {
    
    static IntBinaryOperator suma = (sumando1, sumando2) -> sumando1 + sumando2;
    static IntBinaryOperator resta = (minuendo, sustraendo) -> minuendo - sustraendo;
    
    static IntBinaryOperator multiplica = (factor1, factor2) -> {
        Integer multiplicacionPositiva = IntStream.range(0, Math.abs(factor2)+1)
            .reduce((acumulador, factor) -> suma.applyAsInt(acumulador , Math.abs(factor1)))
            .getAsInt();
        if ((factor1<0 && factor2>0) || (factor1>0 && factor2<0))
            return resta.applyAsInt(0, multiplicacionPositiva);
        return multiplicacionPositiva;
    };

    static final IntBinaryOperator divide = (a,b) -> {
        return IntStream.range(0,Math.abs(a) )
                .reduce((acumulador, numero) ->
                        multiplica.applyAsInt(numero, b) <= a ? suma.applyAsInt(acumulador, 1) : acumulador).getAsInt();

    };


    public static void main(String[] args) throws Exception {
        

        System.out.println(suma.applyAsInt(2,8));
        System.out.println(resta.applyAsInt(8,2));
        System.out.println(multiplica.applyAsInt(4,5));//20
        System.out.println(divide.applyAsInt(20, 4));


    }
}
