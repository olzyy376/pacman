import bagel.Image;

abstract public class CanBeEaten extends Entity{
    private int score;
    private boolean isActive;

    public CanBeEaten(int initialX, int initialY, Image image, int score) {
        super(initialX, initialY, image);
        this.score = score;
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public void update() {
        if (isActive){
            super.update();
        }
    }
}
