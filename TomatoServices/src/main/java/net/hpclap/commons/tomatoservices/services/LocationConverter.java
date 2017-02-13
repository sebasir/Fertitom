package net.hpclap.commons.tomatoservices.services;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import net.hpclap.commons.tomatoservices.model.Location;

@ManagedBean
@ApplicationScoped
public class LocationConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Location p = new Location();
        p.setId(Integer.parseInt(value));
        for (Location l : Util.locations) {
            if (l.equals(p)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Location)) {
            return null;
        }

        return String.valueOf(((Location) value).getId());
    }
}
