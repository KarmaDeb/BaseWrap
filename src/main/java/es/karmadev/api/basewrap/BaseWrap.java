package es.karmadev.api.basewrap;

/**
 * Base wrap utility class
 */
public final class BaseWrap {

    private final WrapDictionary dictionary;

    /**
     * Initialize the base wrap utility
     * class
     *
     * @param dictionary the dictionary to use
     */
    public BaseWrap(final WrapDictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Wrap the data into a string
     *
     * @param blockSize the block size
     * @param data the data to wrap
     * @return the wrapped data string
     */
    public String wrapToString(final int blockSize, final byte[] data) {
        return new String(wrap(blockSize, data));
    }

    /**
     * Wrap the data into a string
     *
     * @param blockSize the block size
     * @param data the data to wrap
     * @return the wrapped data string
     */
    public String wrapToString(final int blockSize, final String data) {
        return new String(wrap(blockSize, data));
    }

    /**
     * Wrap the data
     *
     * @param blockSize the block size
     * @param data the data to wrap
     * @return the wrapped data
     */
    public byte[] wrap(final int blockSize, final byte[] data) {
        if (data == null) throw new NullPointerException("Data cannot be null");
        if (data.length <= blockSize) return data.clone();

        byte[] source = data.clone();

    }

    /**
     * Wrap the data
     *
     * @param blockSize the block size
     * @param data the data to wrap
     * @return the wrapped data
     */
    public byte[] wrap(final int blockSize, final String data) {
        if (data == null) throw new NullPointerException("Data cannot be null");
        return wrap(blockSize, data.getBytes());
    }
}
