class BerkeleyClock { 
    private int hours; 
    private int minutes; 
    public BerkeleyClock(int initialHours, int initialMinutes) { 
    this.hours = initialHours % 24; 
    this.minutes = initialMinutes % 60; 
    } 
    public void updateTime(int adjustmentMinutes) { 
    int totalMinutes = hours * 60 + minutes + adjustmentMinutes; 
    hours = (totalMinutes / 60) % 24; 
    minutes = totalMinutes % 60; 
    } 
    public int getTotalMinutes() { 
    return hours * 60 + minutes; 
    } 
    public String getTime() { 
    return String.format("%02d:%02d", hours, minutes); 
    } 
    } 