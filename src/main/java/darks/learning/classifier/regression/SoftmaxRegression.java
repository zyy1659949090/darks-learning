/**
 * 
 * Copyright 2014 The Darks Learning Project (Liu lihua)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package darks.learning.classifier.regression;

import org.jblas.DoubleMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import darks.learning.neuron.activate.Activations;

/**
 * Logistic regression
 * 
 * @author Darks.Liu
 *
 */
public class SoftmaxRegression extends Regression
{
	
	private static Logger log = LoggerFactory.getLogger(SoftmaxRegression.class);
	
	protected double tolerance = 1.0e-6;
	
	public SoftmaxRegression()
	{
		config.setActivateFunction(Activations.softmax());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void trainBatch(DoubleMatrix input, DoubleMatrix output)
	{
		log.info("Training softmax regression.");
		double startLearnRate = config.learnRate;
		learnRate = startLearnRate;
		initWeight(input, output);
		int iterCount = config.maxIteratorCount;
		double lastLoss = 0;
		for (int i = 1; i <= iterCount; i++)
		{
			if (!config.useAdaGrad)
			{
				learnRate = startLearnRate + (1 / (double)(i + 3));
			}
			iterator(input, output);
			double costValue = calcuateLossValue(input, output);
			if (log.isDebugEnabled() && i % 1000 == 0)
			{
				log.debug("Iterator:" + i + " cost:" + costValue + " lr:" + (config.useAdaGrad ? "Adagrad" : learnRate));
			}
			if (Math.abs(costValue - lastLoss) < tolerance) 
			{
                log.info ("Gradient Ascent: Value difference " + Math.abs(costValue - lastLoss) +" below " +
                        "tolerance; arriving converged.");
                break;
            }
			lastLoss = costValue;
		}
	}
	
	private void initWeight(DoubleMatrix input, DoubleMatrix output)
	{
		weights = DoubleMatrix.rand(input.columns, output.columns);
		bias = DoubleMatrix.zeros(1, output.columns);
		if (log.isDebugEnabled())
		{
			log.debug("Softmax input:[" + input.rows + "," + input.columns 
					+ "] output:[" + output.rows + "," + output.columns + "]");
			log.debug("Softmax weights:[" + weights.rows + "," + weights.columns 
						+ "] bias:[" + bias.rows + "," + bias.columns + "]");
		}
	}
	
	private void iterator(DoubleMatrix input, DoubleMatrix output)
	{
		if (config.randomGradient)
		{
			randomGradientDescent(input, output);
		}
		else
		{
			gradientDescent(input, output);
		}
	}
	
	private void randomGradientDescent(DoubleMatrix input, DoubleMatrix output)
	{
		int rows = input.rows;
		for (int i = 0; i < rows; i++)
		{
			int index = config.randomFunction.randInt(rows);
			DoubleMatrix rowMatrix = input.getRow(index);
			gradientDescent(rowMatrix, output.getRow(index));
		}
	}
	
	private double calcuateLossValue(DoubleMatrix input, DoubleMatrix output)
	{
		DoubleMatrix f = config.activateFunction.activate(input.mmul(weights));
		config.lossFunction.setActiveValue(f);
		config.lossFunction.setInput(input);
		config.lossFunction.setOutput(output);
		config.lossFunction.setWeights(weights);
		config.lossFunction.sethBias(bias);
		return -config.lossFunction.getLossValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DoubleMatrix predict(DoubleMatrix input)
	{
		return config.activateFunction.activate(input.mmul(weights));
	}

}
