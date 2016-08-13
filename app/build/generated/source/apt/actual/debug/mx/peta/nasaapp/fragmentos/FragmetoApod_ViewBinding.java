// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp.fragmentos;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import mx.peta.nasaapp.R;

public class FragmetoApod_ViewBinding<T extends FragmetoApod> implements Unbinder {
  protected T target;

  public FragmetoApod_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.imagen = finder.findRequiredViewAsType(source, R.id.frag_apod_imagen, "field 'imagen'", ImageView.class);
    target.titulo = finder.findRequiredViewAsType(source, R.id.frag_apod_titulo, "field 'titulo'", TextView.class);
    target.fecha = finder.findRequiredViewAsType(source, R.id.frag_apod_fecha, "field 'fecha'", TextView.class);
    target.explicacion = finder.findRequiredViewAsType(source, R.id.frag_apod_explicacion, "field 'explicacion'", TextView.class);
    target.copyright = finder.findRequiredViewAsType(source, R.id.frag_apod_copyright, "field 'copyright'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imagen = null;
    target.titulo = null;
    target.fecha = null;
    target.explicacion = null;
    target.copyright = null;

    this.target = null;
  }
}
