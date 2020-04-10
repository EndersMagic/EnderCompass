package hohserg.endercompass.util.render.elix_x.baked.pipeline.model;

import hohserg.endercompass.util.render.elix_x.baked.UnpackedSimpleBakedModel;
import hohserg.endercompass.util.render.elix_x.ecomms.pipeline.PipelineElement;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class Packer {

	public static PipelineElement<SimpleBakedModel, UnpackedSimpleBakedModel> unpack(){
		return UnpackedSimpleBakedModel::unpack;
	}

	public static PipelineElement<UnpackedSimpleBakedModel, SimpleBakedModel> pack(VertexFormat format){
		return model -> model.pack(format);
	}

}
