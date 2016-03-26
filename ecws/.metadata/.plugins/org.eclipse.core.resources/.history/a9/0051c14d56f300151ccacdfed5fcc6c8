package ninja.sef.simpleindex.crawler;

import java.util.Date;

public class Game {
	private String title;
	private Date releaseDate;
	private String description;
	private String filePath;
	private String system;
	

    public Game() {}
    
    public Game(String title, String system, Date releaseDate, String description,
			String filePath) {
		this.title = title;
		this.system = system;
		this.releaseDate = releaseDate;
		this.description = description;
		this.filePath = filePath;
	}

	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
	    int index = filePath.lastIndexOf(System.getProperty("file.separator"));
	    return filePath.substring(index+1);
	}

    @Override
    public String toString() {
        return "Game [title=" + title + ", releaseDate=" + releaseDate
                + ", description=" + description + ", filePath=" + filePath
                + ", system=" + system + "]";
    }
	
}
