package hohserg.endercompass.util.render.elix_x.ecomms.pipeline.list;

import hohserg.endercompass.util.render.elix_x.ecomms.pipeline.PipelineElement;

import java.util.List;

/**
 * This is a more advanced element of a pipeline. It allows to pipe multiple elements through at once.
 * 
 * @author elix_x
 *
 * @param <I>
 *            Input(s)
 * @param <O>
 *            Output(s)
 */
public interface ListPipelineElement<I, O> extends PipelineElement<List<I>, List<O>> {}
