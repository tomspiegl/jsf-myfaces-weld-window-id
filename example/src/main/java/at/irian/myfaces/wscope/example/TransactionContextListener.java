package at.irian.myfaces.wscope.example;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowContext;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.WindowScoped;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class TransactionContextListener implements PhaseListener {


    public void afterPhase(PhaseEvent event) {
        WindowContext windowContext = event.getFacesContext()
                .getApplication().evaluateExpressionGet(event.getFacesContext(), "#{currentWindowContext}", WindowContext.class);
        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            String source = event.getFacesContext().getExternalContext().getRequestParameterMap().get("source");
            if (source != null && source.equals("menu")) {
                try {
                    windowContext.closeConversation(WindowScoped.class);
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     * @history	BIKAZAR; 05.06.2012; Anlage.
     */
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * @see javax.faces.event.PhaseListener#getPhaseId()
     * @history	BIKAZAR; 05.06.2012; Anlage.
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
