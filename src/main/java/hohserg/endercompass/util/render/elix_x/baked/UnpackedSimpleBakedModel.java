package hohserg.endercompass.util.render.elix_x.baked;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import hohserg.endercompass.Main;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;

import java.util.List;

public class UnpackedSimpleBakedModel {

    private List<UnpackedBakedQuad> generalQuads;
    private ListMultimap<Direction, UnpackedBakedQuad> faceQuads;
    private boolean ambientOcclusion;
    private boolean gui3d;
    private TextureAtlasSprite texture;
    private ItemCameraTransforms cameraTransforms;
    private ItemOverrideList itemOverrideList;

    public UnpackedSimpleBakedModel(List<UnpackedBakedQuad> generalQuads, ListMultimap<Direction, UnpackedBakedQuad> faceQuads, boolean ambientOcclusion, boolean gui3d, TextureAtlasSprite texture, ItemCameraTransforms cameraTransforms, ItemOverrideList itemOverrideList) {
        this.generalQuads = generalQuads;
        this.faceQuads = faceQuads;
        this.ambientOcclusion = ambientOcclusion;
        this.gui3d = gui3d;
        this.texture = texture;
        this.cameraTransforms = cameraTransforms;
        this.itemOverrideList = itemOverrideList;
    }

    public List<UnpackedBakedQuad> getGeneralQuads() {
        return generalQuads;
    }

    public void setGeneralQuads(List<UnpackedBakedQuad> generalQuads) {
        this.generalQuads = generalQuads;
    }

    public List<UnpackedBakedQuad> getFaceQuads(Direction facing) {
        return faceQuads.get(facing);
    }

    public void setFaceQuads(Direction facing, List<UnpackedBakedQuad> faceQuads) {
        this.faceQuads.removeAll(facing);
        this.faceQuads.putAll(facing, faceQuads);
    }

    public ListMultimap<Direction, UnpackedBakedQuad> getFaceQuads() {
        return faceQuads;
    }

    public void setFaceQuads(ListMultimap<Direction, UnpackedBakedQuad> faceQuads) {
        this.faceQuads = faceQuads;
    }

    public boolean isAmbientOcclusion() {
        return ambientOcclusion;
    }

    public void setAmbientOcclusion(boolean ambientOcclusion) {
        this.ambientOcclusion = ambientOcclusion;
    }

    public boolean isGui3d() {
        return gui3d;
    }

    public void setGui3d(boolean gui3d) {
        this.gui3d = gui3d;
    }

    public TextureAtlasSprite getTexture() {
        return texture;
    }

    public void setTexture(TextureAtlasSprite texture) {
        this.texture = texture;
    }

    public ItemCameraTransforms getCameraTransforms() {
        return cameraTransforms;
    }

    public void setCameraTransforms(ItemCameraTransforms cameraTransforms) {
        this.cameraTransforms = cameraTransforms;
    }

    public ItemOverrideList getItemOverrideList() {
        return itemOverrideList;
    }

    public void setItemOverrideList(ItemOverrideList itemOverrideList) {
        this.itemOverrideList = itemOverrideList;
    }

    public SimpleBakedModel pack(VertexFormat format) {
        return new SimpleBakedModel(Lists.transform(generalQuads, quad -> quad.pack(format)), Multimaps.asMap(Multimaps.transformValues(faceQuads, quad -> quad.pack(format))), ambientOcclusion, gui3d, texture, cameraTransforms, itemOverrideList);
    }

    public static UnpackedSimpleBakedModel unpack(SimpleBakedModel model) {
        ListMultimap<Direction, UnpackedBakedQuad> faceQuads = ArrayListMultimap.create();
        for (Direction facing : Direction.values())
            faceQuads.putAll(facing, Lists.transform(model.getQuads(null, facing, Main.random), UnpackedBakedQuad::unpack));
        return new UnpackedSimpleBakedModel(Lists.transform(model.getQuads(null, null, Main.random), UnpackedBakedQuad::unpack), faceQuads, model.isAmbientOcclusion(), model.isGui3d(), model.getParticleTexture(), model.getItemCameraTransforms(), model.getOverrides());
    }

}
