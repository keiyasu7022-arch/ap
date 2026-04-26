import tkinter as tk
import random as rd

class App(tk.Tk):
    def __init__(self):
        tk.Tk.__init__(self)
        self.title("単語帳アプリ")
        self.geometry("800x600")

        self.words_data = []
        self.current_word = None

        self.grid_rowconfigure(0, weight=1)
        self.grid_columnconfigure(0, weight=1)

        self.main_frame = tk.Frame(self)
        self.main_frame.grid(row=0, column=0, sticky="nsew")

        self.frame1 = tk.Frame(self)
        self.frame1.grid(row=0, column=0, sticky="nsew")

        self.create_main_widgets()
        self.create_test_widgets()

        self.main_frame.tkraise()

#メインフレーム
    def create_main_widgets(self):
        frame=tk.Frame(self.main_frame)
        self.entry1 = tk.Entry(frame)
        self.entry2 = tk.Entry(frame)
        self.label1 = tk.Label(frame, text="単語:")
        self.label2 = tk.Label(frame, text="意味:")
        self.label1.grid(row=0, column=0, padx=5, pady=5)
        self.entry1.grid(row=0, column=1, padx=5, pady=5)
        self.label2.grid(row=1, column=0, padx=5, pady=5)
        self.entry2.grid(row=1, column=1, padx=5, pady=5)

        button_container = tk.Frame(frame)
        button_container.grid(row=2, column=0, columnspan=2, pady=10)

        tk.Button(button_container, text="記録", command=self.kiroku, width=8).pack(side="left", padx=5)
        tk.Button(button_container, text="削除", command=self.delete, width=8).pack(side="left", padx=5)
        tk.Button(frame, text="単語帳へ", command=lambda: self.start_test_page(self.frame1)).grid(row=3, column=0, columnspan=4)
        tk.Button(frame,text="一覧表示", command=self.word_list).grid(row=4, column=0, columnspan=4)
        frame.place(relx=0.5,rely=0.1,anchor="n")

#テストフレーム
    def create_test_widgets(self):
        self.word_label = tk.Label(self.frame1, text="", font=('Helvetica', '35'))
        self.word_label.pack()

        self.imi_label = tk.Label(self.frame1, text="", font=('Helvetica', '20'))
        self.imi_label.pack()

        self.imi_btn = tk.Button(self.frame1, text="意味表示", command=self.show_imi)
        self.imi_btn.pack_forget()
        self.next_btn = tk.Button(self.frame1, text="Next", command=self.tango_next)
        self.next_btn.pack_forget()


        self.start_btn = tk.Button(self.frame1, text="単語テスト開始", command=self.tango_next)
        self.start_btn.pack()

        tk.Button(self.frame1, text="記録帳へ", command=lambda: self.back_main_page(self.main_frame)).pack()

    # テスト画面の操作ボタン表示を一元管理
    def set_test_controls(self, show_imi=False, show_next=False):
        self.imi_btn.pack_forget()
        self.next_btn.pack_forget()

        if show_imi:
            self.imi_btn.config(state=tk.NORMAL)
            self.imi_btn.pack()

        if show_next:
            self.next_btn.config(state=tk.NORMAL)
            self.next_btn.pack()

#記録
    def kiroku(self):
        w = self.entry1.get()
        i = self.entry2.get()
        try:
            if not w or not i: return
            with open('words.txt', 'r', encoding='utf-8') as f:
                lines = f.readlines()
                if f"{w},{i}\n" in lines or f"{i},{w}" in lines:
                    self.entry1.delete(0, tk.END)
                    self.entry2.delete(0, tk.END)
                    return

            with open('words.txt', 'a', encoding='utf-8') as f:
                f.write(f"{w},{i}\n")
            self.entry1.delete(0, tk.END)
            self.entry2.delete(0, tk.END)
        except Exception as e:
            print(f"Error: {e}")

#削除
    def delete(self):
        w = self.entry1.get()
        i = self.entry2.get()
        try:
            if not w or not i: return
            with open('words.txt', 'r', encoding='utf-8') as f:
                lines = f.readlines()

            with open('words.txt', 'w', encoding='utf-8') as f:
                for line in lines:
                    if line.strip() != f"{w},{i}":
                        f.write(line)

            self.entry1.delete(0, tk.END)
            self.entry2.delete(0, tk.END)
        except Exception as e:
            print(f"Error: {e}")

#整理
    def seiri(self):
        self.words_data = []
        try:
            with open('words.txt', 'r', encoding='utf-8') as f:
                lines = f.readlines()
                self.words_data = [line.strip().split(',') for line in lines if ',' in line]
        except FileNotFoundError:
            pass

#メインフレームからテストフレームへ
    def start_test_page(self, page):
        self.seiri()
        page.tkraise()

#テストフレームからメインフレームへ
    def back_main_page(self, page):
        self.start_btn.pack()
        self.word_label['text'] = ""
        self.imi_label['text'] = ""
        self.set_test_controls()
        page.tkraise()

#単語テスト
    def tango_next(self):
        if not self.words_data:
            self.word_label['text'] = "単語がありません"
            self.set_test_controls()
            return

        self.start_btn.pack_forget()
        self.set_test_controls()
        self.imi_label['text'] = ""

        self.current_word = rd.choice(self.words_data)
        self.word_label['text'] = self.current_word[0]
        self.set_test_controls(show_imi=True)

#意味表示
    def show_imi(self):
        if self.current_word:
            self.imi_label['text'] = self.current_word[1]
            self.set_test_controls(show_next=True)
#単語一覧表示
    def word_list(self):
        self.seiri()
        try:
            if hasattr(self, 'list_display'):
                self.list_display.destroy()
            with open('words.txt', 'r', encoding='utf-8') as f:
                lines = f.readlines()
            word_list_str = "".join(lines)
            self.list_display = tk.Text(self.main_frame, height=10, width=30, font=('Helvetica', '12'))
            self.list_display.insert(tk.END, word_list_str)
            self.list_display.config(state=tk.DISABLED)
            self.list_display.place(relx=0.5, rely=0.7, anchor="center")
        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    app = App()
    app.mainloop()
