package darks.learning.test.mlp;

import org.jblas.DoubleMatrix;
import org.junit.Test;

import darks.learning.neuron.mlp.MultiLayerNeuronNetwork;

public class MLPTest
{
    
    @Test
    public void testMLP()
    {
//        double[][] trainX = {
//            {0, 1, 1, 0, 0, 0, 0, 0, 0},
//            {0, 0, 1, 0, 0, 0, 0, 0, 0},
//            {0, 1, 0, 0, 0, 0, 0, 0, 0},
//            {1, 0, 1, 0, 0, 0, 0, 0, 0},
//            {1, 1, 0, 0, 0, 0, 0, 0, 0},
//            {1, 1, 1, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 1, 1, 1, 0, 0, 0},
//            {0, 0, 0, 1, 1, 0, 0, 0, 0},
//            {0, 0, 0, 1, 0, 1, 0, 0, 0},
//            {0, 0, 0, 0, 1, 1, 0, 0, 0},
//            {0, 0, 0, 0, 1, 0, 0, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 1, 1, 1},
//            {0, 0, 0, 0, 0, 0, 1, 1, 0},
//            {0, 0, 0, 0, 0, 0, 1, 0, 1},
//            {0, 0, 0, 0, 0, 0, 0, 1, 1},
//            {0, 0, 0, 0, 0, 0, 0, 1, 0},
//            {0, 0, 0, 0, 0, 0, 1, 0, 0}
//        };
//    
//    double[][] labels = {
//            {1, 0, 0},
//            {1, 0, 0},
//            {1, 0, 0},
//            {1, 0, 0},
//            {1, 0, 0},
//            {1, 0, 0},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 1, 0},
//            {0, 0, 1},
//            {0, 0, 1},
//            {0, 0, 1},
//            {0, 0, 1},
//            {0, 0, 1},
//            {0, 0, 1},
//        }; 
    	
    	double[][] trainX = {
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 1},
            };
        
        double[][] labels = {
                {1, 0},
                {1, 0},
                {1, 0},
                {1, 0},
                {0, 1},
                {0, 1},
                {0, 1},
                {0, 1},
            }; 
    
        MultiLayerNeuronNetwork mlp = new MultiLayerNeuronNetwork();
        mlp.config.setHiddenLayouts(new int[]{16})
                    .setUseAdaGrad(false)
                    .setLearnRate(0.1)
                    .setInputLayerSize(3)
                    .setMomentum(0.7)
                    .setMaxIterateCount(10000)
                    .setInputLayerSize(6)
                    .setOutputLayerSize(2)
                    .setUseRegularization(false);
        mlp.trainBatch(new DoubleMatrix(trainX), new DoubleMatrix(labels));
        
        double[][] testX = {
            {1, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1},
        };
    

    double[][] testLabels = {
            {1, 0, 0},
            {1, 0, 0},
            {1, 0, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 0, 1},
            {0, 0, 1},
            {0, 0, 1}
        };
        
        DoubleMatrix testRet = mlp.predict(new DoubleMatrix(trainX));
        System.out.println(testRet.toString().replace(";", "\n"));
    }
    
}
