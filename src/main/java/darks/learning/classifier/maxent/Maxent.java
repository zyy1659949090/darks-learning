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
package darks.learning.classifier.maxent;

import java.util.ArrayList;
import java.util.List;

import darks.learning.corpus.Documents;

/**
 * Maxent model base class
 * 
 * @author  Darks.Liu
 */
public abstract class Maxent
{
    
    protected List<String> labels = new ArrayList<String>();
    
    /**
     * Train maxent model
     * 
     * @param docs Training documents
     * @param maxIteration Max iteration count
     */
    public abstract MaxentModel train(Documents docs, int maxIteration);
    
    public abstract int predict(String[] input);

    public abstract void loadModel(MaxentModel model);

    public List<String> getLabels()
    {
        return labels;
    }

    public String getLabel(int index)
    {
        return labels.get(index);
    }
    
}
