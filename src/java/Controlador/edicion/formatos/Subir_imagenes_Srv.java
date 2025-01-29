package Controlador.edicion.formatos;

import dao.departamento.Departamento_DAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageBean.empleado.Empleado_MB;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author kike
 */
@WebServlet(name = "Subir_imagenes_Srv", urlPatterns =
{
    "/Subir_imagenes_Srv"
})
public class Subir_imagenes_Srv extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "../../web/formatos/";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * Upon receiving file upload submission, parses the request to read upload
     * data and saves the file on disk.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        try
        {
            if (!ServletFileUpload.isMultipartContent(request))
            {
                response.getWriter().println("Error: El formulario debe tener enctype=multipart/form-data.");
                return;
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new java.io.File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            List<FileItem> formItems = upload.parseRequest(request);
            InputStream imageStream = null;
            String fileName = null;

            for (FileItem item : formItems)
            {
                if (!item.isFormField())
                {
                    fileName = new java.io.File(item.getName()).getName();
                    String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

                    if ("jpg".equals(fileExtension) || "jpeg".equals(fileExtension) || "png".equals(fileExtension))
                    {
                        imageStream = item.getInputStream();
                        if (imageStream == null || imageStream.available() <= 0)
                        {
                            System.out.println("El archivo de imagen está vacío o no es válido.");
                            request.setAttribute("message", "Error: El archivo de imagen está vacío o no es válido.");
                            response.sendRedirect("views/EdicionFormatos/imagenes.jsp");
                            return;
                        }
                    } else
                    {
                        System.out.println("Formato de archivo no permitido: " + fileExtension);
                        request.setAttribute("message", "Error: Solo se permiten archivos JPG, JPEG y PNG.");
                        response.sendRedirect("views/EdicionFormatos/imagenes.jsp");
                        return;
                    }
                }
            }

            if (imageStream == null)
            {
                System.out.println("No se encontró un archivo de imagen en la solicitud.");
                request.setAttribute("message", "Error: No se encontró un archivo de imagen en la solicitud.");
                response.sendRedirect("views/EdicionFormatos/imagenes.jsp");
                return;
            }

            // Obtener el ID del departamento desde la sesión
            HttpSession session = request.getSession();
            Empleado_MB empleado_mb = (Empleado_MB) session.getAttribute("empleado");
            if (empleado_mb == null || empleado_mb.getDeptoID() <= 0)
            {
                System.out.println("El empleado o el ID del departamento no son válidos.");
                request.setAttribute("message", "Error: El empleado o el ID del departamento no son válidos.");
                response.sendRedirect("views/EdicionFormatos/imagenes.jsp");
                return;
            }

            int idDepartamento = empleado_mb.getDeptoID();
            System.out.println("ID del departamento obtenido: " + idDepartamento);

            // Guardar la imagen en la base de datos
            Departamento_DAO departamento_dao = new Departamento_DAO();
            departamento_dao.actualizaFormatoDepto(idDepartamento, imageStream);

            request.setAttribute("message", "La imagen se guardó correctamente para el departamento: " + idDepartamento);
            response.sendRedirect("views/EdicionFormatos/imagenes.jsp");

        } catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                response.getWriter().println("Error: " + e.getMessage());
            } catch (Exception ignored)
            {
            }
        }
    }

}
