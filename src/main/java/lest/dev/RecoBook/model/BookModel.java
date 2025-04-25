package lest.dev.RecoBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDate;

@Entity
public class BookModel {

    @Id
    private Long id;
    private String name;
    private String genre;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    @Lob
    private String evaluation;

    public BookModel() {
    }

    public BookModel(Long id, String name, String genre, LocalDate dateStart, LocalDate dateEnd, String evaluation) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }


}
