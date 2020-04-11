package hohserg.endercompass.util.render.elix_x.baked.vertex;

import hohserg.endercompass.util.render.elix_x.ecomms.color.RGBA;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import javax.vecmath.Vector2f;
import java.util.HashMap;
import java.util.Map;

public class DefaultUnpackedVertex {

    /**
     * Unpacked version of {@linkplain DefaultVertexFormats#POSITION_3F}
     */
    private Vec3d pos;
    /**
     * Unpacked version of {@linkplain DefaultVertexFormats#COLOR_4UB}
     */
    private RGBA color;
    /**
     * Unpacked version of {@linkplain DefaultVertexFormats#TEX_2F}
     */
    private Vector2f texture;
    /**
     * Unpacked version of {@linkplain DefaultVertexFormats#TEX_2S}<br><br>
     * Note: Only {@linkplain Vec3i#x} and {@linkplain Vec3i#y} are used, and as valid shorts.
     */
    private Vec3i lightmap = new Vec3i(0, 0, 1);
    /**
     * Unpacked version of {@linkplain DefaultVertexFormats#NORMAL_3B}<br><br>
     * Note: All fields of {@linkplain Vec3i} are valid bytes.
     */
    private Vec3i normal;
    private Map<VertexFormatElement, float[]> unknown = new HashMap<>();

    public DefaultUnpackedVertex(Vec3d pos, RGBA color, Vector2f texture, Vec3i lightmap, Vec3i normal) {
        this.pos = pos;
        this.color = color;
        this.texture = texture;
        this.lightmap = lightmap;
        this.normal = normal;
    }

    public DefaultUnpackedVertex(VertexFormat format, float[][] data) {
        elements:
        for (int i = 0; i < data.length; i++) {
            VertexFormatElement element = format.getElement(i);
            float[] edata = data[i];
            switch (element.getUsage()) {
                case POSITION:
                    if (element.getType() == Type.FLOAT && element.getElementCount() == 3) {
                        pos = new Vec3d(edata[0], edata[1], edata[2]);
                        continue elements;
                    } else break;
                case COLOR:
                    if (element.getType() == Type.UBYTE && element.getElementCount() == 4) {
                        color = new RGBA(edata[0], edata[1], edata[2], edata[3]);
                        continue elements;
                    } else break;
                case UV:
                    if (element.getType() == Type.FLOAT && element.getElementCount() == 2) {
                        texture = new Vector2f(edata[0], edata[1]);
                        continue elements;
                    } else if (element.getType() == Type.SHORT && element.getElementCount() == 2) {
                        lightmap = new Vec3i((short) edata[0], (short) edata[1], 0);
                        continue elements;
                    } else break;
                case NORMAL:
                    if (element.getType() == Type.BYTE && element.getElementCount() == 3) {
                        normal = new Vec3i((byte) edata[0], (byte) edata[1], (byte) edata[2]);
                        continue elements;
                    } else break;
                case PADDING:
                    continue elements;
                default:
                    break;
            }
            unknown.put(element, edata);
        }
    }

    public Vec3d getPos() {
        return pos;
    }

    public DefaultUnpackedVertex setPos(Vec3d pos) {
        this.pos = pos;
        return this;
    }

    public RGBA getColor() {
        return color;
    }

    public DefaultUnpackedVertex setColor(RGBA color) {
        this.color = color;
        return this;
    }

    public Vector2f getTexture() {
        return texture;
    }

    public DefaultUnpackedVertex setTexture(Vector2f texture) {
        this.texture = texture;
        return this;
    }

    public Vec3i getLightmap() {
        return lightmap;
    }

    public DefaultUnpackedVertex setLightmap(Vec3i lightmap) {
        this.lightmap = lightmap;
        return this;
    }

    public Vec3i getNormal() {
        return normal;
    }

    public DefaultUnpackedVertex setNormal(Vec3i normal) {
        this.normal = normal;
        return this;
    }

    public Map<VertexFormatElement, float[]> getUnknown() {
        return unknown;
    }

    public DefaultUnpackedVertex setUnknown(Map<VertexFormatElement, float[]> unknown) {
        this.unknown = unknown;
        return this;
    }

    public float[] getUnknown(VertexFormatElement element) {
        return unknown.get(element);
    }

    public DefaultUnpackedVertex setUnknown(VertexFormatElement element, float[] data) {
        unknown.put(element, data);
        return this;
    }

    public PackedVertex pack(VertexFormat format) {
        float[][] data = new float[format.getElementCount()][];
        elements:
        for (int i = 0; i < data.length; i++) {
            VertexFormatElement element = format.getElement(i);
            float[] edata = data[i] = new float[element.getElementCount()];
            switch (element.getUsage()) {
                case POSITION:
                    if (element.getType() == Type.FLOAT && element.getElementCount() == 3) {
                        edata[0] = (float) pos.x;
                        edata[1] = (float) pos.y;
                        edata[2] = (float) pos.z;
                        continue elements;
                    } else break;
                case COLOR:
                    if (element.getType() == Type.UBYTE && element.getElementCount() == 4) {
                        edata[0] = color.getRF();
                        edata[1] = color.getGF();
                        edata[2] = color.getBF();
                        edata[3] = color.getAF();
                        continue elements;
                    } else break;
                case UV:
                    if (element.getType() == Type.FLOAT && element.getElementCount() == 2) {
                        edata[0] = texture.x;
                        edata[1] = texture.y;
                        continue elements;
                    } else if (element.getType() == Type.SHORT && element.getElementCount() == 2) {
                        edata[0] = (short) lightmap.getX();
                        edata[1] = (short) lightmap.getY();
                        continue elements;
                    } else break;
                case NORMAL:
                    if (element.getType() == Type.BYTE && element.getElementCount() == 3) {
                        edata[0] = (byte) normal.getX();
                        edata[1] = (byte) normal.getY();
                        edata[2] = (byte) normal.getZ();
                        continue elements;
                    } else break;
                case PADDING:
                    continue elements;
                default:
                    break;
            }
            if (unknown.containsKey(element)) System.arraycopy(unknown.get(element), 0, edata, 0, edata.length);
        }
        return new PackedVertex(format, data);
    }

}
