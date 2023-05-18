package codingWithMoshExampleOOP.mytube;

public class VideoEncoder implements XVideoEncoder {
    @Override
    public void encode(Video video) {
        System.out.println("Encoding video...");
        System.out.println("Done!\n");
    }
}
