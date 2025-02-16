class Space {
    private String name;
    private String type;
    private String description;
    private int price;
    private Player owner;
    private int rent;

    public Space(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.owner = null;

        // Extract price and rent from description if it's a property
        if (type.equals("Property") || type.equals("Railroad") || type.equals("Utility")) {
            String[] parts = description.split(", ");
            for (String part : parts) {
                if (part.startsWith("Price: $")) {
                    this.price = Integer.parseInt(part.substring(8));
                }
                if (part.startsWith("Rent: $")) {
                    this.rent = Integer.parseInt(part.substring(7));
                }
            }
        }
    }

    // Add getters and setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {
        return rent;
    }

}
