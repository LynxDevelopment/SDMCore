package com.lynxspa.fpm.nodes.patch;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.OutputConnectable;

/**
 * A node that iterates on a {@link java.lang.Iterable} object and forward the contained objects one by one.<br>
 * In case the following execution throws an exception the current item is sent through the exception output
 * and if the exception is handled (i.e.: not thrown back) the node keeps iterating, execution stops otherwise.
 *
 * @author luca.zenti
 *
 * @param <OUT> The type of the iterable element, hence the type of the output message.
 */
@NodeBeautifier(description = "Patched Collection Iteration Node", 
				smallIcon = "../../../../lynxit/fpm/nodes/icons/iteration_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/iteration_32.gif",
				category = "Information Flow")
public class PatchedCollectionIteratorNode<OUT> extends BasicIteratorNode<OUT> implements OutputConnectable<Iterable<OUT>>
{
    private OutputConnectable< ? super OUT> nodeConnectedToOut_;

    public void connectNodeToOut(OutputConnectable< ? super OUT> node)
    {
        nodeConnectedToOut_ = node;
    }

    public OutputConnectable< ? super OUT> getNodeConnectedToOut()
    {
        return nodeConnectedToOut_;
    }

    public void process(Iterable<OUT> message) throws Exception
    {
    	super.process(message.iterator());
    }
}
