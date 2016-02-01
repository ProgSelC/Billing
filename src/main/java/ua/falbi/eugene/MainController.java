package ua.falbi.eugene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/")
public class MainController
{
	@Autowired
	private MyDAO myDAO;

	// Конвертация строк в utf-8 для коректной работы с кириллицей
	private String getUtfString(final String paramValue) throws UnsupportedEncodingException
	{
		return new String(paramValue.getBytes("iso-8859-1"), "utf-8");
	}


	@RequestMapping("/")
	public ModelAndView listClis()
	{
		return new ModelAndView("index", "clis", myDAO.list());
	}

    @RequestMapping("/payments")
    public ModelAndView listPayments(@RequestParam(value = "id") long id){

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("cli", myDAO.clientById(id));
        model.put("payments", myDAO.payLog(id));

        return new ModelAndView("payments", "model", model);
    }

	@RequestMapping(value = "/add_page", method = RequestMethod.POST)
	public String addPage(Model model)
	{
		return "add_page";
	}


	@RequestMapping(value = "/view_trash", method = RequestMethod.POST)
	public ModelAndView viewTrash()
	{
		return new ModelAndView("trash", "clis", myDAO.trashList());
	}


	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="pattern") String pattern)
	{
		return new ModelAndView("index", "clis", myDAO.list(pattern));
	}


	@RequestMapping("/image/{file_id}")
	public void getFile(HttpServletRequest request,
						HttpServletResponse response,
						@PathVariable("file_id") long fileId)
	{
		try {
			byte[] content = myDAO.getLogo(fileId);
			response.setContentType("image/png");
			response.getOutputStream().write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addCli(@RequestParam(value = "name") String name,
                               @RequestParam(value = "tarif") Double tarif,
                               @RequestParam(value = "balance") Double balance,
                               @RequestParam(value = "logo") MultipartFile inputLogo,
                               HttpServletRequest request,
                               HttpServletResponse response)
	{
		try {
			if (!name.isEmpty() && tarif != null && !tarif.isNaN() && tarif > 0.0 && balance != null && !balance.isNaN()  ) {
				name = getUtfString(name);

				Logo photo = null;

				if (!inputLogo.isEmpty())
					photo =	new Logo(inputLogo.getOriginalFilename(), inputLogo.getBytes());

				Client cli = new Client(name, tarif, balance, photo);
				myDAO.add(cli);
			}
			return new ModelAndView("redirect:/");
		} catch (IOException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ModelAndView("redirect:/");
		}
	}

	// Безвозвратное удаление клиента
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="id") long id)
	{
		myDAO.delete(id);
		return new ModelAndView("redirect:/");
	}


	// Безвозвратное удаление выделенных клиентов
	@RequestMapping(value = "/delete_selected")
	public ModelAndView deleteSelected(@RequestParam(required = false, value = "selected") long[] ids)
	{
		myDAO.delete(ids);
		return new ModelAndView("redirect:/");
	}


	// Удаление клиента в корзину
	@RequestMapping("/remove")
	public ModelAndView remove(@RequestParam(value="id") long id)
	{
		myDAO.remove(id);
		return new ModelAndView("redirect:/");
	}


	// Удаление выделенных клиентов в корзину
	@RequestMapping(value = "/remove_selected")
	public ModelAndView removeSelected(@RequestParam(required = false, value = "selected") long[] ids)
	{
		myDAO.remove(ids);
		return new ModelAndView("redirect:/");
	}


	// Восстановление клиента из корзины
	@RequestMapping("/restore")
	public ModelAndView restore(@RequestParam(value="id") long id)
	{
		myDAO.restore(id);
		return new ModelAndView("redirect:/");
	}


	// Восстановление выделенных клиентов из корзины
	@RequestMapping(value = "/restore_selected")
	public ModelAndView restoreSelected(@RequestParam(required = false,value = "selected") long[] ids)
	{
		myDAO.restore(ids);
		return new ModelAndView("redirect:/");
	}

	// Списание абонплаты
	@RequestMapping("/writeoff")
	public ModelAndView writeoff(@RequestParam(value="id") long id)
	{
		myDAO.writeoffMoney(id);
		return new ModelAndView("redirect:/");
	}

    @RequestMapping("/writeoff_selected")
    public ModelAndView writeoffSelected(@RequestParam(required = false, value = "selected") long[] ids)
    {
        myDAO.writeoffSelectedMoney(ids);
        return new ModelAndView("redirect:/");
    }

	// Зачисление суммы
	@RequestMapping("/deposit")
	public ModelAndView deposit(@RequestParam(value="id") long id, @RequestParam(value="sum") long sum)
	{
		myDAO.depositMoney(id, sum);
		return new ModelAndView("redirect:/");
	}

}