package hohserg.endercompass.util.render.elix_x.baked.pipeline.quad;

import hohserg.endercompass.util.render.elix_x.baked.UnpackedBakedQuad;
import hohserg.endercompass.util.render.elix_x.baked.pipeline.vertex.VertexMatrixTransformer;
import hohserg.endercompass.util.render.elix_x.baked.vertex.DefaultUnpackedVertices;
import hohserg.endercompass.util.render.elix_x.ecomms.pipeline.Pipeline;
import hohserg.endercompass.util.render.elix_x.ecomms.pipeline.PipelineElement;
import hohserg.endercompass.util.render.elix_x.ecomms.pipeline.list.ToListTransformersPipelineElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;
import org.lwjgl.util.vector.Matrix4f;

public class QuadMatrixTransformer implements PipelineElement<UnpackedBakedQuad, UnpackedBakedQuad> {

	public static QuadMatrixTransformer toFace(EnumFacing facing){
		return new QuadMatrixTransformer(TRSRTransformation.getMatrix(facing));
	}

	private javax.vecmath.Matrix4f matrix;
	private VertexMatrixTransformer vertexTransformer = new VertexMatrixTransformer(new Matrix4f());
	private final PipelineElement<DefaultUnpackedVertices, Void> vertexTransformerPipeline = new Pipeline<>(new ToListTransformersPipelineElement.Iterable(), vertexTransformer);

	public QuadMatrixTransformer(Matrix4f matrix){
		setMatrix(matrix);
	}

	public QuadMatrixTransformer(javax.vecmath.Matrix4f matrix){
		setMatrix(matrix);
	}

	public Matrix4f getMatrix(){
		return TRSRTransformation.toLwjgl(matrix);
	}

	public void setMatrix(Matrix4f matrix){
		this.matrix = TRSRTransformation.toVecmath(matrix);
		this.vertexTransformer.setMatrix(matrix);
	}

	public void setMatrix(javax.vecmath.Matrix4f matrix){
		this.matrix = matrix;
		this.vertexTransformer.setMatrix(matrix);
	}

	@Override
	public UnpackedBakedQuad pipe(UnpackedBakedQuad in){
		in.setFace(TRSRTransformation.rotate(matrix, in.getFace()));
		vertexTransformerPipeline.pipe(in.getVertices());
		return in;
	}
}
