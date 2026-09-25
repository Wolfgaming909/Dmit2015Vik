package dmit2015.views;

import dmit2015.model.CampusEvent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CampusEventBean implements Serializable {

    private List<CampusEvent> eventList = new ArrayList<>();
    private CampusEvent newCampusEvent = new CampusEvent();
    private int nextEventId = 2601;

    public List<CampusEvent> getEventList() {
        return eventList;
    }

    public CampusEvent getNewCampusEvent() {
        return newCampusEvent;
    }

    public void setNewCampusEvent(CampusEvent newCampusEvent) {
        this.newCampusEvent = newCampusEvent;
    }

    public String createEvent() {
        newCampusEvent.setEventId(nextEventId);
        nextEventId++;
        eventList.add(newCampusEvent);

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Event created: " + newCampusEvent.getEventSummary()));

        newCampusEvent = new CampusEvent();

        return "view-events?faces-redirect=true";
    }
}
