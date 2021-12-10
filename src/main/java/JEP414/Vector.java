package JEP414;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class Vector {

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public static void main(String[] args) {
        float[] a = {1F, 2F};
        float[] b = {3F, 4F};
        float[] c = new float[2];

        newVectorComputation(a, b, c);
        System.out.println(c[0] + " " + c[1]);

    }

    public static void newVectorComputation(float[] a, float[] b, float[] c) {
        for (var i = 0; i < a.length; i += SPECIES.length()) {
            var m = SPECIES.indexInRange(i, a.length);
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va.mul(vb);
            vc.intoArray(c, i, m);
        }
    }

    public static void commonVectorComputation(float[] a, float[] b, float[] c) {
        for (var i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }
    }

}
