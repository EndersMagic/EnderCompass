package hohserg.endercompass.util.render.elix_x.baked;

import hohserg.endercompass.util.render.elix_x.baked.vertex.DefaultUnpackedVertices;
import hohserg.endercompass.util.render.elix_x.baked.vertex.PackedVertices;
import hohserg.endercompass.util.render.elix_x.ecomms.reflection.ReflectionHelper.AClass;
import hohserg.endercompass.util.render.elix_x.ecomms.reflection.ReflectionHelper.AField;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad.Builder;
import org.lwjgl.opengl.GL11;

public class UnpackedBakedQuad {

    private VertexFormat format;

    private DefaultUnpackedVertices vertices;
    private int tintIndex;
    private Direction face;
    private TextureAtlasSprite sprite;
    private boolean applyDiffuseLighting;

    public UnpackedBakedQuad(DefaultUnpackedVertices vertices, int tintIndex, Direction face, TextureAtlasSprite sprite, boolean applyDiffuseLighting) {
        this.vertices = vertices;
        this.tintIndex = tintIndex;
        this.face = face;
        this.sprite = sprite;
        this.applyDiffuseLighting = applyDiffuseLighting;
    }

    public UnpackedBakedQuad(PackedVertices vertices, int tintIndex, Direction face, TextureAtlasSprite sprite, boolean applyDiffuseLighting) {
        this(vertices.unpack(), tintIndex, face, sprite, applyDiffuseLighting);
        format = vertices.getFormat();
    }

    public UnpackedBakedQuad(float[][][] vertexData, VertexFormat format, int tintIndex, Direction face, TextureAtlasSprite sprite, boolean applyDiffuseLighting) {
        this(new PackedVertices(GL11.GL_QUADS, format, vertexData), tintIndex, face, sprite, applyDiffuseLighting);
    }

    public DefaultUnpackedVertices getVertices() {
        return vertices;
    }

    public void setVertices(DefaultUnpackedVertices vertices) {
        this.vertices = vertices;
    }

    public int getTintIndex() {
        return tintIndex;
    }

    public void setTintIndex(int tintIndex) {
        this.tintIndex = tintIndex;
    }

    public Direction getFace() {
        return face;
    }

    public void setFace(Direction face) {
        this.face = face;
    }

    public TextureAtlasSprite getSprite() {
        return sprite;
    }

    public void setSprite(TextureAtlasSprite sprite) {
        this.sprite = sprite;
    }

    public boolean isApplyDiffuseLighting() {
        return applyDiffuseLighting;
    }

    public void setApplyDiffuseLighting(boolean applyDiffuseLighting) {
        this.applyDiffuseLighting = applyDiffuseLighting;
    }

    public BakedQuad pack() {
        return pack(format);
    }

    public BakedQuad pack(VertexFormat format) {
        return new net.minecraftforge.client.model.pipeline.UnpackedBakedQuad(vertices.pack(GL11.GL_QUADS, format).getData(), tintIndex, face, sprite, applyDiffuseLighting, format);
    }

    private static final AField<net.minecraftforge.client.model.pipeline.UnpackedBakedQuad, float[][][]> unpackedData = new AClass<>(net.minecraftforge.client.model.pipeline.UnpackedBakedQuad.class).<float[][][]>getDeclaredField("unpackedData").orElseThrow(() -> new IllegalArgumentException("Failed to reflect forge.UnpackedBakedQuad#unpackedData necessary for excore.UnpackedBakedQuad")).setAccessible(true);

    public static UnpackedBakedQuad unpack(net.minecraftforge.client.model.pipeline.UnpackedBakedQuad quad) {
        return new UnpackedBakedQuad(unpackedData.get(quad).get(), quad.getFormat(), quad.getTintIndex(), quad.getFace(), quad.getSprite(), quad.shouldApplyDiffuseLighting());
    }

    //TODO: Move to functional models utils class once created.
    @Deprecated
    public static net.minecraftforge.client.model.pipeline.UnpackedBakedQuad unpackForge(BakedQuad quad) {
        if (quad instanceof net.minecraftforge.client.model.pipeline.UnpackedBakedQuad)
            return (net.minecraftforge.client.model.pipeline.UnpackedBakedQuad) quad;
        Builder builder = new Builder(quad.getFormat());
        quad.pipe(builder);
        return builder.build();
    }

    public static UnpackedBakedQuad unpack(BakedQuad quad) {
        return unpack(unpackForge(quad));
    }

}
